package pl.sklep.config;

import org.springframework.stereotype.Component;

@Component
public class PriceFormatter {

    public String format(Integer price) {
        int msd = price / 100;
        int lsd = price - 100 * msd;
        String result = String.format("%d.%2dzł", msd, lsd).replace(' ', '0');
        for (int i = result.indexOf('.') - 3; i > 0; i = result.indexOf(',') - 3) {
            result = result.substring(0, i) + ',' + result.substring(i);
        }
        return result;
    }

}
