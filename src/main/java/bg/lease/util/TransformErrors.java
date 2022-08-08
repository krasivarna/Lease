package bg.lease.util;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class TransformErrors {

    public List<String> listOfErrors(List<FieldError> fieldErrors){
        List<String> result=new ArrayList<>();
        for(FieldError oneError:fieldErrors){
            result.add("Field: "+oneError.getField()+" - "+oneError.getDefaultMessage());
        }

        return result;
    }
}
