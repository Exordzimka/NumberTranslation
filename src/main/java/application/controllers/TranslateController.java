package application.controllers;

import application.models.NumberTranslationUser;
import application.models.UserHistory;
import application.repositories.UserHistoryRepository;
import application.repositories.UserRepository;
import application.translation.PreTranslator;
import application.translation.exceptions.WordIncorrectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/translate")
public class TranslateController {
    @Autowired
    UserHistoryRepository userHistoryRepository;
    @Autowired
    UserRepository userRepository;
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public Map<String, String> convert(@RequestBody Map<String, String> body, Model model) throws WordIncorrectException {
        PreTranslator numberConvertor = new PreTranslator();
        String input = body.get("inputText");
        String language = body.get("language");
        String fromTo = body.get("fromTo");
        String translatedNumber = numberConvertor.translate(input, language, fromTo);
        translatedNumber = translatedNumber==null || translatedNumber.isBlank()?"Can not translate!": translatedNumber;
        addHistory(input, translatedNumber);
        String finalTranslatedNumber = translatedNumber;
        return new HashMap<>(){{
            put("translatedNumber", finalTranslatedNumber);
        }};
    }

    private void addHistory(String beforeTranslate, String afterTranslate){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserHistory userHistory = new UserHistory();
        NumberTranslationUser user = userRepository.findByUsername(authentication.getName());
        userHistory.setDateTime(LocalDateTime.now());
        userHistory.setNumberTranslationUser(user);
        userHistory.setBeforeTranslate(beforeTranslate);
        userHistory.setAfterTranslate(afterTranslate);
        userHistoryRepository.save(userHistory);
    }
}
