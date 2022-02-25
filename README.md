# Projeto Default para Automação em Java
Utilizando as seguintes tecnologias:
- Cucumber
- Junit
- Selenium
- Allure
> Proposta de Arquitetura para realizar Testes Automatizados em Java
> Setar o no diretóriochromeDriver [drivers](./project_default/src/test/resources/drivers/) 
## Start
> utilizandi o terminal dentro do diretório do [projeto](./project_default/) utilizar os <u><b>seguintes comandos:</b></u>
```bash
   mvn install
```
> Este irá instalar as depedências no projeto
```bash
   mvn clean verify
```
> Este irá rodar os testes e criar o cucumber report personalizado - lembrando que irá apagar os históricos
```bash
   mvn verify
```
> Este irá rodar os testes e criar o cucumber report personalizado - manterndo os históricos
```bash
   mvn test
```
> Este irá rodar os cenários, no entanto não irá criar o cucumber report personalizado, mas será criados:
- cucumber report default
- allure report
