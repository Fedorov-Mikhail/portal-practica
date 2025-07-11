package com.example.portal.utils.Codified;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public enum CodifiedEnum implements CodifiedMessage<Integer> {
    E500(500, "Системная ошибка"),
    E603(603, "Не все обязательные поля заполнены"),
    E606(606, "Указанный адрес электронной почты уже существует"),
    E607(607, "Пароль уже использовался"),
    E610(610, "Введен неверный пароль"),
    E611(611, "Было передано некорректное значение %s - %s"),
    E612(612, "Не удалось найти адрес по указанному идентификатору"),
    E613(613, "Ошибка передачи данных %s: %s. Не существует!"),
    E614(614, "Заявка которую необходимо отредактировать - отсутствует"),
    E615(615, "Ошибка статусной модели"),
    E616(616, "Запись не найдена"),
    E618(618, "Ошибка! Невозможно удалить запись. Объект имеет связь с %s : %s"),
    E701(701, "Новый пароль не соответствует требованиям"),
    E705(705, "Не все данные получены"),
    E708(708, "Пользователь с таким логином уже существует.");

    private final Integer code;
    private final String description;

    @Override
    public boolean is(Long id) {
        return getCode() == id.byteValue();
    }

}