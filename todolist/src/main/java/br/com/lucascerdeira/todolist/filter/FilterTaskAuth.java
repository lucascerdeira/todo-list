package br.com.lucascerdeira.todolist.filter;

import java.io.IOException;
import java.util.Base64;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.lucascerdeira.todolist.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// toda class que eu quero o spring gerenciando 
@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.equals("/tasks/")) {
            //Pegar a autenticação
            var authorization = request.getHeader("Authorization");
        
            var authEncoded = authorization.substring("Basic".length()).trim();

            // craindo um array de bytes
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecode);

            String[] credentials =  authString.split(":");
            String username = credentials[0];
            String password = credentials[1];
            System.out.println("Authorization");
            System.out.println(username);
            System.out.println(password);


            // validar user
            var user = this.userRepository.findByUsername(username);
            if(user == null ){
                response.sendError(401);
            } else { // validar password
                        
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if(passwordVerify.verified) {
                    filterChain.doFilter(request, response);
                } else { 
                    response.sendError(401);
                } 
                        
            } 

        } else {

            filterChain.doFilter(request, response);
        }
       
        
    }

        
        
}

    
    

