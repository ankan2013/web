package spring.utils;

import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class ErrorRedirect {

    private static Map<String, String> errors;

    public static void errorInitialize(){
        errors = new HashMap<String, String>();
        errors.put("empty_field", "Необходимо заполнить все поля");
        errors.put("database_err", "Ошибка базы данных");
        errors.put("ordered_disk_err", "Нельзя изменять или удалять носитель, находящийся в прокате");
        errors.put("incorrect_date_format", "Пожалуйста, введите даты в формате YYYY-MM-DD");
        errors.put("incorrect_return_date", "Дата возврата должна быть позже даты взятия");
    }

    public static ModelAndView error(String msg_id){
        return new ModelAndView("redirect:/error/?msg_id="+msg_id);
    }

    public static String getError(String msg_id){
        return errors.get(msg_id);
    }

}
