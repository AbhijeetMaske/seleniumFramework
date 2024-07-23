# **Locator**

### Locator Naming Conventions

This document outlines the naming conventions for various UI/control types when creating locators in Selenium. Consistent naming helps in maintaining clarity and understanding throughout the test automation suite.

---

<img  align= center width=50px height=50px src="https://media4.giphy.com/media/3hoLIVAJYkz6T0Ichp/giphy.gif?cid=6c09b952m4j3poopinf91rquev6qy4e8avu0bflq1e0vh4gp&ep=v1_internal_gif_by_id&rid=giphy.gif&ct=s"> <a id="table-of-contents">Table of Contents</a></br>

- [Basic Controls](#basic-controls)
- [Complex Controls](#complex-controls)

## Basic Controls

| Category |  UI/Control type           | Prefix | Example         |
| :--------| :------------------------- | :-----:| -------         |
| Basic    | Button                     | btn    | btnExit         |
| Basic    | Check box                  | chk    | chkReadOnly     |
| Basic    | Combo box                  | cbo    | cboEnglish      |
| Basic    | Common dialog              | dlg    | dlgFileOpen     |
| Basic    | Date picker                | dtp    | dtpPublished    |
| Basic    | Dropdown List / Select tag | ddl    | ddlCountry      |
| Basic    | Form                       | frm    | frmEntry        |
| Basic    | Frame                      | fra    | fraLanguage     |
| Basic    | Image                      | img    | imgIcon         |
| Basic    | Label                      | lbl    | lblHelpMessage  |
| Basic    | Links/Anchor Tags          | lnk    | lnkForgotPwd    |
| Basic    | List box                   | lst    | lstPolicyCodes  |
| Basic    | Menu                       | mnu    | mnuFileOpen     |
| Basic    | Radio button / group       | rdo    | rdoGender       |
| Basic    | RichTextBox                | rtf    | rtfReport       |
| Basic    | Table                      | tbl    | tblCustomer     |
| Basic    | TabStrip                   | tab    | tabOptions      |
| Basic    | Text Area                  | txa    | txaDescription  |
| Basic    | Text box                   | txt    | txtLastName     |


## Complex Controls

| Category |  UI/Control type           | Prefix | Example         |
| :--------| :------------------------- | :-----:| -------         |
| Complex  | Chevron                    | chv    | chvProtocol     |
| Complex  | Data grid                  | dgd    | dgdTitles       |
| Complex  | Data list                  | dbl    | dblPublisher    |
| Complex  | Directory list box         | dir    | dirSource       |
| Complex  | Drive list box             | drv    | drvTarget       |
| Complex  | File list box              | fil    | filSource       |
| Complex  | Panel/Fieldset             | pnl    | pnlGroup        |
| Complex  | ProgressBar                | prg    | prgLoadFile     |
| Complex  | Slider                     | sld    | sldScale        |
| Complex  | Spinner                    | spn    | spnPages        |
| Complex  | StatusBar                  | sta    | staDateTime     |
| Complex  | Timer                      | tmr    | tmrAlarm        |
| Complex  | Toolbar                    | tlb    | tlbActions      |
| Complex  | TreeView                   | tre    | treOrganization |


** Guidelines **

- 1.Consistency: Always use the specified prefixes to maintain consistency across the project.
- 2.Readability: Choose meaningful names that clearly describe the UI element's purpose.
- 3. Clarity: Ensure the locator names are clear and understandable to anyone reading the code.

By adhering to these naming conventions, you ensure that your Selenium locators are easy to understand, maintain, and use, leading to a more efficient and manageable test automation suite.
