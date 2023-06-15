package com.dms.demo.controlleradvice;

import com.dms.demo.util.enums.Gender;
import com.dms.demo.util.enums.MusicGenre;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

@ControllerAdvice
public class GlobalInitBinder {
    @InitBinder("gender")
    public void initGenderBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Gender.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(Gender.fromDbValue(text));
            }
        });
    }

    @InitBinder("musicGenre")
    public void initMusicGenreBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(MusicGenre.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(MusicGenre.fromDbValue(text));
            }
        });
    }
}