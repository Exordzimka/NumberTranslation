create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create table number_translation_user
(
    id       serial       not null
        constraint number_translation_user_pk
            primary key,
    password varchar      not null,
    active   boolean default true,
    username varchar(255) not null
);

alter table number_translation_user
    owner to postgres;

create unique index number_translation_user_username_uindex
    on number_translation_user (username);

create table user_history
(
    id               serial  not null
        constraint user_history_pk
            primary key,
    user_id          integer not null
        constraint user_history_number_translation_user_id_fk
            references number_translation_user,
    after_translate  varchar not null,
    before_translate varchar not null,
    date_time        date    not null
);

alter table user_history
    owner to postgres;

create table number_translation_user_role
(
    user_id integer
        constraint number_translation_user_role_number_translation_user_id_fk
            references number_translation_user
            on update cascade on delete cascade,
    roles   varchar
);

alter table number_translation_user_role
    owner to postgres;

