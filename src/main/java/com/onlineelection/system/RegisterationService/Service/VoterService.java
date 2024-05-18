
// package com.onlineelection.system.RegisterationService.Service;

// import com.onlineelection.system.RegisterationService.Entity.Voter;
// import
// com.onlineelection.system.RegisterationService.Repository.VoterRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;

// @Service
// public class VoterService {

// @Autowired
// private VoterRepository voterRepository;

// private final String[] firstNames = { "Abebe", "Berhanu", "Chaltu", "Dawit",
// "Eleni", "Fikir", "Girma", "Haimanot",
// "Iyasu", "Jerusalem" };
// private final String[] lastNames = { "Alemu", "Belay", "Chala", "Dereje",
// "Endale", "Fikadu", "Gebre", "Hagos",
// "Ibrahim", "Jemal" };
// private final String[] branches = { "Ayder", "Quiha", "Business", "MIT",
// "Main Campus", "Veterinary" };
// private final String[] departments = { "Software Engineering", "Computer
// Science", "Chemical Engineering",
// "Civil Engineering", "Mechanical Engineering", "Information System" };

// public void generateAndSaveFakeStudents(int count) {
// List<Voter> students = generateFakeStudents(count);
// voterRepository.saveAll(students);
// }

// private List<Voter> generateFakeStudents(int count) {
// List<Voter> students = new ArrayList<>();
// Random random = new Random();

// for (int i = 0; i < count; i++) {
// String firstName = firstNames[random.nextInt(firstNames.length)];
// String lastName = lastNames[random.nextInt(lastNames.length)];
// String branch = branches[random.nextInt(branches.length)];
// String department = departments[random.nextInt(departments.length)];
// int section = random.nextInt(3) + 1;
// double grade = 2.0 + (random.nextDouble() * (4.0 - 2.0));
// int yearOfStudy = random.nextInt(4) + 1;
// String studentId = generateStudentId(branch);

// Voter voter = new Voter(firstName, lastName, branch, department, section,
// grade, yearOfStudy, studentId);
// students.add(voter);
// }

// return students;
// }

// private String generateStudentId(String branch) {
// StringBuilder studentId = new StringBuilder();
// studentId.append(branch.toLowerCase().replaceAll("\\s+", ""));

// if (branch.equalsIgnoreCase("Ayder")) {
// studentId.append("/").append(generateRandomNumber(6));
// } else if (branch.equalsIgnoreCase("Quiha")) {
// studentId.append("/eitm/ur").append(generateRandomNumber(6));
// } else if (branch.equalsIgnoreCase("Business")) {
// studentId.append("/soc/ur").append(generateRandomNumber(6));
// } else if (branch.equalsIgnoreCase("Main Campus")) {
// studentId.append("/main").append(generateRandomNumber(6));
// } else {
// studentId.append("/vet").append(generateRandomNumber(6));
// }

// return studentId.toString();
// }

// private String generateRandomNumber(int length) {
// StringBuilder sb = new StringBuilder();
// Random random = new Random();

// for (int i = 0; i < length; i++) {
// sb.append(random.nextInt(10));
// }

// return sb.toString();
// }
// }
