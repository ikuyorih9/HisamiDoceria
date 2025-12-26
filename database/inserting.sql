/* --- EMPLOYEE ROLE ---*/
INSERT INTO
    EMPLOYEE_ROLE (
        name,
        description,
        can_sell,
        can_edit_menu,
        can_change_roles,
        can_register_employee
    )
VALUES (
        'GERENTE',
        'Gerencia a doceria e tem todos os acessos',
        TRUE,
        TRUE,
        TRUE,
        TRUE
    ),
    (
        'ATENDENTE',
        'Atende os clientes e pode vender',
        TRUE,
        FALSE,
        FALSE,
        FALSE
    ),
    (
        'COZINHEIRO',
        'Prepara os produtos, não pode mexer em menu nem vendas',
        FALSE,
        TRUE,
        FALSE,
        FALSE
    );

/* --- EMPLOYEE ---*/
INSERT INTO
    EMPLOYEE (cpf, name)
VALUES (
        '04604799202',
        'Hugo Hiroyuki Nakamura'
    );

/*--- EMPLOYEE ACCOUNT ---*/

INSERT INTO
    EMPLOYEE_ACCOUNT (
        username,
        email,
        password,
        init_date,
        role
    )
VALUES (
        'ikuyorih9',
        'hugohiroyuki9@gmail.com',
        '$2a$10$M2F0aPxxl5tRN3XyOqaU9ei9LxXuuKjz3aUhcjK97ZrEOf5Du8Nsi
',
        CURRENT_DATE,
        'GERENTE'
    )

/*--- HAS ACCOUNT ---*/

INSERT INTO
    HAS_ACCOUNT (
        employee_cpf,
        employee_username
    )
VALUES ('04604799202', 'ikuyorih9')