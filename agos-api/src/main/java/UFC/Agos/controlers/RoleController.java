package UFC.Agos.controlers;


import UFC.Agos.Dto.RoleToProfessorForm;
import UFC.Agos.models.Professor;
import UFC.Agos.models.Role;
import UFC.Agos.models.Student;
import UFC.Agos.services.imp.ProfessorService;
import UFC.Agos.services.imp.StudentService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController @Slf4j
public class RoleController {

    @Autowired
    ProfessorService professorService;

    @Autowired
    StudentService studentService;

    @ApiOperation(value = "Add role to professor")
    @PostMapping(path = "/api/role/addToUser")
    public ResponseEntity<?> addRoleToProfessor(@RequestBody RoleToProfessorForm form)  {
        professorService.addRoleToProfessor(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Refresh Token")
    @GetMapping(path = "/api/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();

                Professor professor = professorService.findProfessorByUsername(username);
                Student student = studentService.findStudentByUsername(username);
                String access_token;

                if(professor != null) {
                    access_token = JWT.create()
                        .withSubject(professor.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", professor.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                } else
                {
                     access_token = JWT.create()
                            .withSubject(student.getUsername())
                            .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                            .withIssuer(request.getRequestURL().toString())
                            .withClaim("roles", student.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                            .sign(algorithm);

                    Map<String, String> tokens = new HashMap<>();
                    tokens.put("access_token", access_token);
                    tokens.put("refresh_token", refresh_token);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                }
            } catch (Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
             throw new RuntimeException("Refresh token is missing");
        }
    }
}
