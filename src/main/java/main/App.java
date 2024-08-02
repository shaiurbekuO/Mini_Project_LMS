package main;

import main.config.DatabaseConfig;
import main.entity.*;
import main.enums.FamilyStatus;
import main.enums.Gender;
import main.enums.HouseType;
import main.service.*;
import main.service.impl.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Scanner scannerN = new Scanner(System.in);
        Scanner scannerS = new Scanner(System.in);
        AgencyService agencyService = new AgencyServiceImpl();
        AddressService addressService = new AddressServiceImpl();
        OwnerService ownerService = new OwnerServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        HouseService houseService = new HouseServiceImpl();
        while (true){
            System.out.println("""
                    Agency                      Customer
            1. Save                         17. Save
            2. Find by id                   18. SaveCustomerAndSaveRentInfo 
            3. Update                       19. Find by id 
            4. find All                     20. Update 
            5. Delete                       21. Find All
            6. Assign Agency to Address     22. Delete
                    Address                     House
            7. assign Agency And Address    23. Save
            8. Бир метод ар бир адрести агентсвосу менен чыгарсын. 24. House-тузулуп + Owner
            9. Update                       25. Find by id
            10. find All                    26. Update
            11. Delete                      27. Find All
                                            28. Delete 
                    Owner                       Agency koshumcha method 
            12. Save                        29.Agency очкондо адреси жана rent_info кошо очсун
                                                Address
            13. Find by id                  30.  Бир метод ар бир адрести агентсвосу менен чыгарсын.
            14. Update                      31. Колдонуучу бир шаардын атын жазса ошол шаарда канча агентсво бар экенин эсептеп чыгарсын                                     
            15. find All
            16. Delete
            
            """);
            switch(scannerN.nextInt()){
                case 1 ->{
                    Agency agency = new Agency("paeksoft", 252352);
                    System.out.println("Enter Address:");
                    System.out.println(agencyService.save(agency));
                }
                case 2 ->{
                    System.out.println("write id:");
                    Agency agency = agencyService.findById(scannerN.nextLong());
                    System.out.println(agency);
                }
                case 3 ->{
                    System.out.println("Write id:");
                    String message = agencyService.update(scannerN.nextLong(), new Agency("HII",42324));
                    System.out.println(message);
                }
                case 4 ->{
                    System.out.println("write id:");
                    List<Agency> agencies = agencyService.findAll();
                    agencies.forEach(System.out::println);
                }
                case 5 ->{
                    System.out.println("write id:");
                    String message = agencyService.deleteById(scannerN.nextLong());
                    System.out.println(message);
                }
                case 6 -> {
                    System.out.println("write Agency id:");
                    Long agencyId = scannerN.nextLong();
                    System.out.println("write Address id:");
                    Long addressId = scannerN.nextLong();
                    String message = agencyService.assignAgencyToAddress(agencyId, addressId);
                    System.out.println(message);
                }
                case 7 ->{
                    System.out.println("write id:");
                    Address address = new Address("Bishkek","Dordoy Bazaar","Ibragimova");
                    Agency agency = new Agency("Hous", 252323352);
                    agency.setAddress(address);
                    String message = agencyService.assignAgencyAndAddress(agency);
                    System.out.println(message);
                }
                case 8 ->{
                    System.out.println("write id:");
//                    Бир метод ар бир адрести агентсвосу менен чыгарсын.
                    Address address = addressService.AddressAndAgency(scannerN.nextLong());

                }
                case 9 ->{
                    System.out.println("write id:");
                    Address address = addressService.findById(scannerN.nextLong());
                    System.out.println(address);
                }
                case 10 ->{
                    System.out.println("Write id:");
                    String message = addressService.update(scannerN.nextLong(), new Address("Jalal-Abad","Hooken","Maasy"));
                    System.out.println(message);
                }
                case 11 ->{
                    System.out.println("write id:");
                    List<Address> addresses = addressService.findAll();
                    addresses.forEach(System.out::println);
                }
//                case 12 ->{
//                    System.out.println("write id:");
//                    String message = addressService.deleteById(scannerN.nextLong());
//                    System.out.println(message);
//                }
                case 12 -> {
                    System.out.println("write id:");
                    Owner owner = new Owner("Aigul", "Huraly kyzy", "Aigul@gmail.com", LocalDate.of(2003, 5,6), Gender.FEMALE);
                    String message = ownerService.save(owner);
                    System.out.println(message);
                }
                case 13 ->{
                    System.out.println("write id:");
                    Owner owner = ownerService.findById(scannerN.nextLong());
                    System.out.println(owner);

                }
                case 14 ->{
                    System.out.println("write id:");
                    String message = ownerService.update(scannerN.nextLong(), new Owner());
                    System.out.println(message);
                }
                case 15 -> {
                    System.out.println("write id:");
                    List<Owner> owners = ownerService.findAll();
                    owners.forEach(System.out::println);
                }
                case 16 -> {
                    System.out.println("write id:");
                    String message = ownerService.delete(scannerN.nextLong());
                    System.out.println(message);
                }
                case 17 ->{
                    System.out.println("write id:");
                    Customer customer = new Customer("Omurbek", "Sh","SH@gmail.com", LocalDate.of(2024, 2,8), LocalDate.of(2024, 8 ,8), Gender.MALE, "fdsg", FamilyStatus.COHABITING);
                    Customer message = customerService.saveCustomer(customer);
                    System.out.println(message);
                }
                case 18 -> {
                    System.out.println("write id:");
                    RentInfo rentInfo = new RentInfo("каттоого киргизүү", "текшерүү");
                    Customer customer = new Customer("Belek", "Asanov","T@gmail.com", LocalDate.of(2024, 6,18), LocalDate.of(2024, 10,18), Gender.MALE, "fdsg", FamilyStatus.DIVORCED);
                    customer.setRentInfo(rentInfo);
                    String message = customerService.saveCustomerAndRentInfo(customer);
                    System.out.println(message);
                }
                case 19 -> {
                    System.out.println("write id:");
                    Customer message = customerService.findById(scannerN.nextLong());
                    System.out.println(message);
                }
                case 20 ->{
                    System.out.println("write id:");
                    Customer customer = new Customer("Usen", "Asanov","U@gmail.com", LocalDate.of(2024, 3,18), LocalDate.of(2024, 9,18), Gender.MALE, "fdsgsfds", FamilyStatus.SEPARATED);
                    String message = customerService.update(scannerN.nextLong(), customer);
                    System.out.println(message);
                }
                case 21 ->{
                    System.out.println("write id:");
                    List<Customer> customers = customerService.getAll();
                    customers.forEach(System.out::println);
                }
                case 22 ->{
                    System.out.println("write id:");
                    String message = customerService.delete(scannerN.nextLong());
                    System.out.println(message);
                }
                case 23 ->{
                    System.out.println("write id:");
                    House house = new House(HouseType.APARTMENT, BigDecimal.valueOf(353543), 534.534, "house", 23, true);
                    String message = houseService.save(house);
                    System.out.println(message);
                }
                case 24 ->{
                    System.out.println("write id:");
                    Owner owner = new Owner("Omurbek", "Shaiyrbek uulu", "Omurbekfff@gmail.com", LocalDate.of(2002, 2,5), Gender.MALE);
                    House house = new House(HouseType.COTTAGE, BigDecimal.valueOf(500000), 734.34, "Pend House", 17, false);
                    house.setOwner(owner);
                    String message = houseService.save(house);
                    System.out.println(message);
                }
                case 25 ->{
                    System.out.println("write id:");
                    House house = houseService.findById(scannerN.nextLong());
                    System.out.println(house);
                }
                case 26 -> {
                    System.out.println("write id:");
                    House house = new House(HouseType.SEMI_DETACHED, BigDecimal.valueOf(502000), 44.500, "Duu X", 10, false);
                    String message = houseService.update(scannerN.nextLong(), house);
                    System.out.println(message);
                }
                case 27 ->{
                    System.out.println("write id:");
                    List<House> houses = houseService.getAll();
                    System.out.println(houses);
                }
                case 28 -> {
                    System.out.println("write id:");
                    String message = houseService.delete(scannerN.nextLong());
                    System.out.println(message);
                }
                case 29 ->{
//                    Agency очкондо адреси жана rent_info кошо очсун
                    System.out.println("write id:");
                    String message = agencyService.deleteAgencyAndAddressAndRentInfo(scannerN.nextLong());
                    System.out.println(message);
                }
                case 30 -> {
//                    Бир метод ар бир адрести агентсвосу менен чыгарсын.
                    System.out.println("write id:");
                    List<Address> addresses = addressService.AddressOzuMNAgencyAlachsyn();
                    System.out.println(addresses);
                }
                case 31 ->{
                    System.out.println("write id:");
//                    Колдонуучу бир шаардын атын жазса ошол шаарда канча агентсво бар экенин эсептеп чыгарсын
                    List<Address> address = addressService.AddressCityAgency(scannerS.nextLine());
                    System.out.println(address);
                }
            }
        }

    }
}
