package tr.com.huseyinaydin.interceptors;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import org.springframework.util.StringUtils;
import tr.com.huseyinaydin.services.EncryptionService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@Component
public class EncryptionInterceptor extends EmptyInterceptor {

    /*@Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        System.out.println("Ben onFlushDirty metoduyum.");
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("Ben onLoad metoduyum.");
        return super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("Ben onSave metoduyum.");
        return super.onSave(entity, id, state, propertyNames, types);
    }*/

    private final EncryptionService encryptionService;

    public EncryptionInterceptor(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        System.out.println("Ben onFlushDirty metoduyum.");
        Object[] newState = processFields(entity, currentState, propertyNames, "onFlushDirty");

        return super.onFlushDirty(entity, id, newState, previousState, propertyNames, types);
    }

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("Ben onLoad metoduyum.");
        Object[] newState = processFields(entity, state, propertyNames, "onLoad");

        return super.onLoad(entity, id, newState, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("Ben onSave metoduyum.");
        Object[] newState = processFields(entity, state, propertyNames, "onSave");

        return super.onSave(entity, id, newState, propertyNames, types);
    }

    private Object[] processFields(Object entity, Object[] state, String[] propertyNames, String type) {
        List<String> annotationFields = getAnnotationFields(entity);

        for (String field : annotationFields) {
            for (int i = 0; i < propertyNames.length; i++) {
                if (propertyNames[i].equals(field)) {
                    if (StringUtils.hasText(state[i].toString())) {
                        if ("onSave".equals(type) || "onFlushDirty".equals(type)) {
                            state[i] = encryptionService.encrypt(state[i].toString());
                        } else if ("onLoad".equals(type)) {
                            state[i] = encryptionService.decrypt(state[i].toString());
                        }
                    }
                }
            }
        }

        return state;
    }

    private List<String> getAnnotationFields(Object entity) {
        List<String> annotatedFields = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (!Objects.isNull(field.getAnnotation(EncryptedString.class))) {
                annotatedFields.add(field.getName());
            }
        }

        return annotatedFields;
    }
}