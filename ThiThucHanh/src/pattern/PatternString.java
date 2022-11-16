package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternString {
    public PatternString(){}
    public boolean valindateEmail(String email){
        String regrex = "^.{8,12}\\@gmail.com|vn$";
        return stringRegrex(regrex, email);
    }
    public boolean stringRegrex(String regrex, String str){
        Pattern pattern = Pattern.compile(regrex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
    public boolean validatePhone(String phone){
        String regrex = "^0[839]\\.[0-9]{4}\\.[0-9]{4}$";
        return stringRegrex(regrex, phone);
    }
}
