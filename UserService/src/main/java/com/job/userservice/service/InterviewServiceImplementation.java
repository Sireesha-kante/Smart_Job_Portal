/*
 * package com.job.userservice.service;
 * 
 * import java.time.LocalDateTime; import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.job.userservice.entity.Interview; import
 * com.job.userservice.entity.RecruiterProfile; import
 * com.job.userservice.repository.InterviewRepository; import
 * com.job.userservice.repository.RecruiterProfileRepository;
 * 
 * @Service public class InterviewServiceImplementation implements
 * InterviewService {
 * 
 * @Autowired private InterviewRepository interviewRepository;
 * 
 * @Autowired private RecruiterProfileRepository recruiterProfileRepository;
 * 
 * @Override public Interview scheduleInterview(Long recruiterId, Long
 * jobApplicationId, LocalDateTime interviewDate, String interviewLink) { //
 * TODO Auto-generated method stub RecruiterProfile recruiter =
 * recruiterProfileRepository.findById(recruiterId) .orElseThrow(() -> new
 * RuntimeException("Recruiter not found"));
 * 
 * Interview interview = new Interview(); interview.setRecruiter(recruiter);
 * interview.setInterviewDate(interviewDate);
 * interview.setInterviewLink(interviewLink); return
 * interviewRepository.save(interview); }
 * 
 * @Override public List<Interview> getScheduledInterviews(Long recruiterId) {
 * return interviewRepository.findByRecruiterId(recruiterId);
 * 
 * }
 * 
 * }
 */