-- Важно, чтобы в имени файла было 2 подчёркивания "__" см (https://flywaydb.org/documentation/migrations#naming)
create table tags
(
    id   serial primary key,
    name varchar(256) unique
);

create table priorities
(
    id   serial primary key,
    name varchar(256) unique,
    rank int unique
);

create table task_lists
(
    id   bigint primary key,
    name varchar(256) unique
);

create sequence task_lists_id_seq;

create table tasks
(
    id           serial primary key,
    name         varchar(256),
    description  text,
    done         boolean,
    priority_id  bigint references priorities (id),
    task_list_id bigint references task_lists (id)
);

create table task_tags
(
    task_id bigint,
    tag_id  bigint,

    primary key (task_id, tag_id)
);

-- можете переиспользовать этот код, для формального удволетворения требований и я зачту без вопросов
-- но если покапаетесь и сделаете чё-нить своё - это будет плюс в карму
create function on_task_list_insert()
    RETURNS "trigger" AS
$BODY$
BEGIN
    New.id := nextval('task_lists_id_seq');
    Return NEW;
END;
$BODY$
    LANGUAGE 'plpgsql' VOLATILE;

create trigger on_task_list_insert_trg
    before insert
    on task_lists
    for each row
execute procedure on_task_list_insert();

insert into task_lists (name)
values ('Default');
