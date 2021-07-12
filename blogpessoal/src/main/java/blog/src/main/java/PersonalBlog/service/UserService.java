package blog.src.main.java.PersonalBlog.service;

import blog.src.main.java.PersonalBlog.model.User;
import blog.src.main.java.PersonalBlog.model.UserLogin;
import org.apache.commons.codec.binary.Base64;
import blog.src.main.java.PersonalBlog.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User RegisterUser(@NotNull User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String passwordEnconder = encoder.encode(user.getPassword());
        user.setPassword(passwordEnconder);

        return repository.save(user);
    }

    public Optional<UserLogin> Login(@NotNull Optional<UserLogin> user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<User> name = repository.findByUser(user.get().getUser());

        if(name.isPresent()) {
            if(encoder.matches(user.get().getPassword(), name.get().getPassword())){
                String auth = user.get().getName() + ":" + user.get().getPassword();
                byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String autHeader = "Basic " + new String(encodeAuth);

                user.get().setToken(autHeader);
                user.get().setName(user.get().getName());

                return user;
            }
        }
        return null;
    }
}
