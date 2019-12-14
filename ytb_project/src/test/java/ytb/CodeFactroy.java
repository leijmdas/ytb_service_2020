package ytb;

import ytb.common.utils.CodeFactory.ModelFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class CodeFactroy {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IOException {

        ModelFactory.build("project_member_task");

    }

}
