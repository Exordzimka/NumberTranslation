package application.controllers;

import application.models.UserHistory;
import application.repositories.UserHistoryRepository;
import application.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/history")

public class HistoryController {
    @Autowired
    UserHistoryRepository userHistoryRepository;
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonIgnore
    public Map<String, Object> getHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<UserHistory> userHistory = userHistoryRepository.findByUsername(authentication.getName());
        Map<String, Object> response = new HashMap<>(){{
            put("history", userHistory);
        }};
        return response;
    }
}
