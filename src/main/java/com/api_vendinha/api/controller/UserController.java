package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.UserRequestActiveDto;
import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gerenciar operações relacionadas aos usuários.
 */
@RestController
@RequestMapping("/api/users") // Define o caminho base para as requisições deste controlador.
public class UserController {

    // Injeção de dependência do serviço de usuários.
    private final UserServiceInterface userService;

    /**
     * Construtor para injeção de dependência do serviço de usuários.
     *
     * @param userService O serviço de usuários a ser injetado.
     */
    @Autowired
    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    /**
     * Método para salvar um novo usuário.
     *
     * @param userRequestDto DTO que contém os dados do usuário a ser salvo.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado.
     */
    @PostMapping // Define que este método lida com requisições HTTP POST.
    public UserResponseDto save(@RequestBody UserRequestDto userRequestDto) {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        System.out.println(userRequestDto);
        return userService.save(userRequestDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUsers(
                @PathVariable Long id) {
        return userService.getUsers(id);
    }

    @PutMapping("/{id}") // Define que este método lida com requisições HTTP PUT.
    public UserResponseDto update(
            @PathVariable Long id,
            @RequestBody UserRequestDto userRequestDto
    ) {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        return userService.update(id, userRequestDto);
    }

    @PutMapping("/{id}/status")
    public UserResponseDto updateStatus(
            @PathVariable Long id,
            @RequestBody UserRequestActiveDto userRequestActiveDto
    ) {
        return userService.updateStatus(id, userRequestActiveDto);
    }

}
