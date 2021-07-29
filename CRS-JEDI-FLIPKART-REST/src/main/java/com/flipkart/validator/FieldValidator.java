package com.flipkart.validator;

import java.lang.reflect.Field;
import java.util.Arrays;

public class FieldValidator {
    public static String isValid(Object obj, String[] names)
    {
        try
        {
            for (Field field : obj.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                String type = field.getType().getSimpleName();
                String name = field.getName();
                if (!Arrays.stream(names).anyMatch(name::equals))
                    continue;
                if (type.equals("int"))
                {
                    if (field.getInt(obj) < 0)
                        return field.getName() + " cannot be negative";
                }
                else if (type.equals("String"))
                {
                    if (field.get(obj) == null || ((String)(field.get(obj))).length() == 0)
                        return field.getName() + " cannot be empty";
                }
            }
        }
        catch(Exception e)
        {
            return null;
        }
        return null;
    }
}
