package pe.edu.upc.tpbackinkametrics;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TpBackInkaMetricsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpBackInkaMetricsApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
