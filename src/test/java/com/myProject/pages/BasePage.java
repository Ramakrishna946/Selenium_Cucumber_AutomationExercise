package com.myProject.pages;

import com.myProject.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(css = ".navbar-nav>li")
    public List<WebElement> tabMenu;

    public List<String> getTabMenuNames(){
        List<String> list = new ArrayList<>();

        for (WebElement menu : tabMenu) {
            list.add(menu.getText());
        }
        return list;
    }

    public void navigateToTabs(String tabName){
        WebElement tab = Driver.get().findElement(By.xpath("//a[contains(text(),'"+tabName+"')]"));
        tab.click();
    }

    public void switchToAdFrame() {
        List<WebElement> frames = Driver.get().findElements(By.tagName("iframe"));
        for (WebElement frame : frames) {
            try {
                String id = frame.getAttribute("id");
                String name = frame.getAttribute("name");

                if ((id != null && id.contains("aswift")) ||
                        (name != null && name.contains("aswift"))) {

                    Driver.get().switchTo().frame(frame);
                    System.out.println("Switched to: " + id);
                    return;
                }
            } catch (Exception ignored) { }
        }
    }


}
