package com.udacity.course3.exercise10;

import com.udacity.course3.exercise10.model.Member;
import com.udacity.course3.exercise10.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.swing.text.html.Option;
import java.util.Optional;

@SpringBootApplication
@EnableMongoRepositories
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
        return args -> {
            // STEP 1: Define Member and MemberRepository first before changing this class
            Member member = new Member();



            // STEP 2: create a Member record
            member.setFirstName("Rodrigo");
            member.setLastName("Santiago");
            member.setAge(29);
            member.setGender("male");


            // STEP 3: save the Member record
            memberRepository.save(member);


            // read the Member using memeber last name
            Optional<Member> looked = memberRepository.findByFirstName("Rodrigo");

            if(looked.isPresent()) {
                System.err.println("Member " + looked.get().getId());
                memberRepository.deleteById(looked.get().getId());
            }
        };
    }
}