package com.example.trail2.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

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

    // Constructors
    public Employee() {}

    public Employee(Integer filenumber, String firstName, String lastName, String gender,
                LocalDate dateOfBirth, String address1, String address2,
                String phoneNumber1, String phoneNumber2) {
        this.filenumber = filenumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address1 = address1;
        this.address2 = address2;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
    }

    // Getters and Setters
    public Integer getFilenumber() {
        return filenumber;
    }

    public void setFilenumber(Integer filenumber) {
        this.filenumber = filenumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }
}

//createEmployee(employee: Employee): Observable<Employee> {
//    return this.http.post<Employee>(this.apiUrl, employee); // No headers
//}
