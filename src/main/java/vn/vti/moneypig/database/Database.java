package vn.vti.moneypig.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.vti.moneypig.models.*;
import vn.vti.moneypig.repositories.*;
import vn.vti.moneypig.security.PasswordEncoder;
import vn.vti.moneypig.utils.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class Database {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, AppConfigRepository appConfigRepository,
                                   CategoryGroupRepository categoryGroupRepository, CommandRepository commandRepository, FinancialDataRepository financialDataRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                User user = new User();
                user.setId(1L);
                user.setUsername("admin");
                user.setEmail("admin@gmail.com");
                user.setPhone("84965741051");
                user.setPassword(PasswordEncoder.getInstance().encodePassword("A123456a@"));
                user.setStatus(1);
                user.setGender(1);
                user.setDateOfBirth(20011010);
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                String formattedDate = currentDate.format(formatter);
                long longDate = Long.parseLong(formattedDate);
                user.setCreatedDate(longDate);
                if(userRepository.findAll().isEmpty())
                    userRepository.insert(user);
                CategoryGroup categoryGroup1 = new CategoryGroup();
                categoryGroup1.setId(1L);
                categoryGroup1.setCode("EXPENSE");
                categoryGroup1.setName("Expense");
                categoryGroup1.setStatus(1);
                categoryGroup1.setCreatedDate(longDate);

                CategoryGroup categoryGroup2 = new CategoryGroup();
                categoryGroup2.setId(2L);
                categoryGroup2.setCode("MONEY");
                categoryGroup2.setName("Money");
                categoryGroup2.setStatus(1);
                categoryGroup2.setCreatedDate(longDate);

                CategoryGroup categoryGroup3 = new CategoryGroup();

                categoryGroup3.setId(3L);
                categoryGroup3.setCode("DEPT");
                categoryGroup3.setName("Dept");
                categoryGroup3.setStatus(1);
                categoryGroup3.setCreatedDate(longDate);

                if(categoryGroupRepository.findAll().isEmpty()){
                    categoryGroupRepository.insert(categoryGroup1);
                    categoryGroupRepository.insert(categoryGroup2);
                    categoryGroupRepository.insert(categoryGroup3);
                }
                AppConfig appConfig = new AppConfig();
                appConfig.setId(1L);
                appConfig.setActive(0);
                if(appConfigRepository.findAll().isEmpty())
                    appConfigRepository.insert(appConfig);


                Command command = new Command();
                command.setId(1L);
                command.setValue(0);
                command.setTimeUTC(DateUtils.getCurrentTimeUTC());
                command.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());
                System.out.println("UTC:"+DateUtils.getCurrentTimeUTC());

                // Command form E5

                Command command2 = new Command();
                command2.setId(2L);
                command2.setValue(0);
                command2.setTimeUTC(DateUtils.getCurrentTimeUTC());
                command2.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());


                // Command form RON95

                Command command3 = new Command();
                command3.setId(3L);
                command3.setValue(0);
                command3.setTimeUTC(DateUtils.getCurrentTimeUTC());
                command3.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());


                // Command form Coffee

                Command command4 = new Command();
                command4.setId(4L);
                command4.setValue(0);
                command4.setTimeUTC(DateUtils.getCurrentTimeUTC());
                command4.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());


                // Command form Coffee Robusta

                Command command5 = new Command();
                command5.setId(5L);
                command5.setValue(0);
                command5.setTimeUTC(DateUtils.getCurrentTimeUTC());
                command5.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());



                // Command form Coffee Arabica

                Command command6 = new Command();
                command6.setId(6L);
                command6.setValue(0);
                command6.setTimeUTC(DateUtils.getCurrentTimeUTC());
                command6.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());


                // Command form Gold SJC

                Command command7 = new Command();
                command7.setId(7L);
                command7.setValue(0);
                command7.setTimeUTC(DateUtils.getCurrentTimeUTC());
                command7.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());

                // Command form BTMC

                Command command8 = new Command();
                command8.setId(8L);
                command8.setValue(0);
                command8.setTimeUTC(DateUtils.getCurrentTimeUTC());
                command8.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());


                // Command from USD
                Command command9 = new Command();
                command9.setId(9L);
                command9.setValue(0);
                command9.setTimeUTC(DateUtils.getCurrentTimeUTC());
                command9.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());


                // Command from EURO
                Command command10 = new Command();
                command10.setId(10L);
                command10.setValue(0);
                command10.setTimeUTC(DateUtils.getCurrentTimeUTC());
                command10.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());


                // Command from BANG ANH
                Command command11 = new Command();
                command11.setId(11L);
                command11.setValue(0);
                command11.setTimeUTC(DateUtils.getCurrentTimeUTC());
                command11.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());
                commandRepository.deleteAll();
                if(commandRepository.findAll().isEmpty()){

                    commandRepository.insert(command);
                    commandRepository.insert(command2);
                    commandRepository.insert(command3);
                    commandRepository.insert(command4);
                    commandRepository.insert(command5);
                    commandRepository.insert(command6);
                    commandRepository.insert(command7);
                    commandRepository.insert(command8);
                    commandRepository.insert(command9);
                    commandRepository.insert(command10);
                    commandRepository.insert(command11);

                }
//                FinancialData financialData1 = new FinancialData();
//                financialData1.set
//                if(serviceRepository.findAll().isEmpty()){
//                    Service service1 = new Service();
//                    service1.setId(1L);
//                    service1.setServiceCode("C-HA123");
//                    service1.setServiceName("ABC");
//                    service1.setServiceType("HHH");
//                    serviceRepository.insert(service1);
//                }
            }
        };
    }
    
}
