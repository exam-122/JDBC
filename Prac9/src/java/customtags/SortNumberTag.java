package customtags;

import java.io.IOException;
import java.util.Arrays;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import jakarta.servlet.jsp.JspWriter;

public class SortNumberTag extends SimpleTagSupport {
    private String numbers; 
    private String order;   

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (numbers == null || numbers.trim().isEmpty()) {
            return;
        }

      
        String[] numArray = numbers.split(",");
        int[] intArray = new int[numArray.length];

        try {
            for (int i = 0; i < numArray.length; i++) {
                intArray[i] = Integer.parseInt(numArray[i].trim());
            }
        } catch (NumberFormatException e) {
            throw new JspException("Invalid number format. Please enter valid integers.");
        }

        
        Arrays.sort(intArray);

       
        if ("desc".equalsIgnoreCase(order)) {
            for (int i = 0; i < intArray.length / 2; i++) {
                int temp = intArray[i];
                intArray[i] = intArray[intArray.length - 1 - i];
                intArray[intArray.length - 1 - i] = temp;
            }
        }

       
        JspWriter out = getJspContext().getOut();
        out.write("Sorted Numbers (" + order + "): " + Arrays.toString(intArray));
    }
}
