Одна из основных идей отделить паттерн PageObject от тестов вынося его в блок Main;
Структура:
1 - Описание страницы;
2 - Определенение полей страницы("FindBy" с указанием на локатор);
3 - Определение в конструкторе Webdriver & PageFactory;
4 - Определение методов этой страницы с созданными полями(переменными);

В тестах реализация экзепляров созданых нами классов паттерна PageObject;
ElementPage - частный случай PageObject
структура такая же как **PageObject**