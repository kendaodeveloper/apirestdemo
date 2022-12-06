package com.example.apirestdemo.service.user;

import com.example.apirestdemo.endpoint.user.request.WriteUserRequest;
import com.example.apirestdemo.entity.base.PageableEntity;
import com.example.apirestdemo.entity.user.User;
import com.example.apirestdemo.exception.ConflictException;
import com.example.apirestdemo.exception.NotFoundException;
import com.example.apirestdemo.storage.user.UserStorage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
  public User getById(Integer id) {
    return UserStorage.users.stream()
        .filter(x -> x.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new NotFoundException("User not found"));
  }

  public PageableEntity<User> getByFilter(
      Integer id, String name, String cpf, String email, LocalDate birthDate, Integer pageNumber, Integer pageSize
  ) {
    List<User> users = UserStorage.users.stream()
        .filter(x -> (id == null || id.equals(x.getId())) &&
            (name == null || x.getName().toLowerCase().contains(name.toLowerCase())) &&
            (cpf == null || cpf.equals(x.getCpf())) &&
            (email == null || email.equals(x.getEmail())) &&
            (birthDate == null || birthDate.equals(x.getBirthDate()))
        ).collect(Collectors.toList());

    return new PageableEntity<>(users, pageNumber, pageSize).doFilter();
  }

  public User create(WriteUserRequest request) {
    UserStorage.users.stream().filter(x ->
        x.getCpf().equals(request.getCpf()) || x.getEmail().equalsIgnoreCase(request.getEmail())
    ).findAny().ifPresent(x -> {
      throw new ConflictException("User already exists by CPF/email");
    });

    User user = new User(
        UserStorage.users.size() + 1,
        request.getName(),
        request.getCpf(),
        request.getEmail(),
        request.getBirthDate(),
        new ArrayList<>()
    );

    UserStorage.users.add(user);

    return user;
  }

  public User update(Integer id, WriteUserRequest request) {
    UserStorage.users.stream().filter(x ->
        !x.getId().equals(id) &&
            (x.getCpf().equals(request.getCpf()) || x.getEmail().equalsIgnoreCase(request.getEmail()))
    ).findAny().ifPresent(x -> {
      throw new ConflictException("Another user already exists by CPF/email");
    });

    User user = UserStorage.users.stream()
        .filter(x -> x.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new NotFoundException("User not found"));

    user.setName(request.getName());
    user.setCpf(request.getCpf());
    user.setEmail(request.getEmail());
    user.setBirthDate(request.getBirthDate());

    return user;
  }

  public void delete(Integer id) {
    User user = UserStorage.users.stream()
        .filter(x -> x.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new NotFoundException("User not found"));

    UserStorage.users.remove(user);
  }
}
