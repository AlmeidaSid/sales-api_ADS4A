package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.UserRequestActiveDto;
import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço de usuários.
 *
 * Esta classe fornece a implementação dos métodos definidos na interface UserServiceInterface,
 * lidando com a lógica de negócios relacionada aos usuários, como criar e atualizar usuários.
 */
@Service
public class UserServiceImpl implements UserServiceInterface {

    // Repositório para a persistência de dados de usuários.
    private final UserRepository userRepository;

    /**
     * Construtor para injeção de dependência do UserRepository.
     *
     * @param userRepository O repositório de usuários a ser injetado.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Salva um novo usuário ou atualiza um usuário existente.
     * <p>
     * Cria uma nova entidade User a partir dos dados fornecidos no UserRequestDto, persiste essa
     * entidade no banco de dados, e retorna um UserResponseDto com as informações do usuário salvo.
     *
     * @param userRequestDto DTO contendo os dados do usuário a ser salvo ou atualizado.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado e o nome.
     */
    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        // Cria uma nova instância de User.
        User user = new User();

        // Define o nome do usuário a partir do DTO.
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setIsActive(userRequestDto.getIsActive());
        user.setDocument(userRequestDto.getDocument());

        // Salva o usuário no banco de dados e obtém a entidade persistida com o ID gerado.
        User savedUser = userRepository.save(user);

        // Cria um DTO de resposta com as informações do usuário salvo.
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setIsActive(savedUser.getIsActive());
        userResponseDto.setDocument(savedUser.getDocument());

        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }

    @Override
    public UserResponseDto getUsers(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow();

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(existingUser.getId());
        userResponseDto.setName(existingUser.getName());
        userResponseDto.setEmail(existingUser.getEmail());
        userResponseDto.setPassword(existingUser.getPassword());
        userResponseDto.setIsActive(existingUser.getIsActive());
        userResponseDto.setDocument(existingUser.getDocument());

        return userResponseDto;
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        // Get the previously created User
        User existingUser = userRepository.findById(id).orElseThrow();

        existingUser.setName(userRequestDto.getName());
        existingUser.setEmail(userRequestDto.getEmail());
        existingUser.setPassword(userRequestDto.getPassword());
        existingUser.setIsActive(userRequestDto.getIsActive());
        existingUser.setDocument(userRequestDto.getDocument());

        userRepository.save(existingUser);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(existingUser.getName());
        userResponseDto.setEmail(existingUser.getEmail());
        userResponseDto.setPassword(existingUser.getPassword());
        userResponseDto.setIsActive(existingUser.getIsActive());
        userResponseDto.setDocument(existingUser.getDocument());

        return userResponseDto;
    }

    @Override
    public UserResponseDto updateStatus(Long id, UserRequestActiveDto userRequestActiveDto) {
        User existingUser = userRepository.findById(id).orElseThrow();

        existingUser.setIsActive(userRequestActiveDto.getIsActive());

        userRepository.save(existingUser);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(existingUser.getId());
        userResponseDto.setName(existingUser.getName());
        userResponseDto.setEmail(existingUser.getEmail());
        userResponseDto.setPassword(existingUser.getPassword());
        userResponseDto.setIsActive(existingUser.getIsActive());
        userResponseDto.setDocument(existingUser.getDocument());

        return userResponseDto;
    }

}
