1. Use below json format to post to "http://localhost:8080/api/employees" 
    {
    "filenumber": 567890,
    "firstName": "Amarnath",
    "lastName": "Kendre",
    "gender": "Male",
    "dateOfBirth": "2010-12-12",
    "address1": "Adilabad",
    "address2": "Hyderabad",
    "phoneNumber1": "9012378346",
    "phoneNumber2": ""
  }


2. Use EmployeeService in the fronted to do CRUD operation on employee data.

3. I have kept this "http://localhost:8080/api/employees" as public URL in SecurityConfig.java file. After all tasks completion we can convert to protected URL.

4. Use AuthGuardService.canActivate() method in the app.routes,ts file to keep it protected

5. In backend I have used class as employee model

  @Document(collection = "employees")
  public class Employee {

    @Id
    @NotNull(message = "File number is required")
    private Integer filenumber;

    @NotBlank(message = "First name is required")
    @Field("firstName")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Field("lastName")
    private String lastName;

    @NotBlank(message = "Gender is required")
    @Field("Gender")
    private String gender;

    @NotNull(message = "Date of birth is required")
    @Field("Date_of_birth")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Address 1 is required")
    @Field("address1")
    private String address1;

    @Field("address2")
    private String address2;

    @NotBlank(message = "Phone number 1 is required")
    @Field("phone_number1")
    private String phoneNumber1;

    @Field("phone_number2")
    private String phoneNumber2;
 }

6. In Angular, if Http Module doesn't importing, add HttpModule to the providers in the app.config.ts

7. Configure routes in app.routes.ts and update in the navbar.component.ts.
