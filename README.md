# Projeto Default para Automação em Java
Utilizando as seguintes tecnologias:
- Cucumber
- Junit
- Selenium
- Allure
> Proposta de Arquitetura para realizar Testes Automatizados em Java
## Setup
> Setar o usário na classe  [Utils](project_default/src/test/java/br/com/project_default/utils/Utils.java) no método:
```java
    public static WebDriver getDriver() {
        ....
    String downloadFilepath = "C:\\Users\\<usuário>\\Downloads";
        ....
    }
```
> Setar o no diretóriochromeDriver [drivers](./project_default/src/test/resources/drivers/) 
## Start
> utilizandi o terminal dentro do diretório do [projeto](./project_default/) utilizar os <u><b>seguintes comandos:</b></u>
```bash
   npm install
```
> Este irá instalar as depedências no projeto
```bash
   npm clean verify
```
> Este irá rodar os testes e criar o cucumber report personalizado - lembrando que irá apagar os históricos
```bash
   npm verify
```
> Este irá rodar os testes e criar o cucumber report personalizado - manterndo os históricos
```bash
   npm test
```
> Este irá rodar os cenários, no entanto não irá criar o cucumber report personalizado, mas será criados:
- cucumber report default
- allure report