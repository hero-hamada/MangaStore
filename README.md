### Инстструкция по развертованию окружения к проекту MangaStore

Среда разработки
o	IntelliJ IDEA
o	JDK 1.8
o	Mysql Workbench 8.0.23
o	Tomcat 8.5.64

### Шаги по настройке проекта 

1.	Откройте Intellij IDEA, в меню перейдите по File → New → Project From Version Control, и в URL вставьте ссылку для клонирования проекта с гитхаб;
2.	Чтобы настроить сервер, в меню перейдите по Run → Edit Configurations. В открывшемся окне нажимаете Add Configuration (+), затем выбираете Tomcat Server, Local;
3.	Во кладке Tomcat Server / Tomcat 8.5.64, нажимаете configure (рядом с Application Server) и указываете путь к заранее установленному серверу Apache Tomcat (у меня это ..\apache-tomcat-8.5.64-windows-x64\apache-tomcat-8.5.64);
4.	Укажите JRE 1.8, и внизу этой же вкладки добавьте новый артефакт проекта с кнопкой +:
5.	Во вкладке Run → Edit Configurations → Deployment, из Application context удалите все и оставьте только “/”;
#### Шаги по соединению БД с проектом
1.	Откройте Mysql Workbench, в меню перейдите по Server → Data Import  и выберите Import From Self-Contained File, и укажите путь к файлу db_dump.sql;
2.	В качестве Default target schema выбираете свою либо создаете новую, например mangastore;
3.	Во вкладке Import Progress, нажимаете Start Import;
4.	Убедитесь что ваши настройки доступа к БД (url, user, password) совпадают с настройками в файле db.properties (..\MangaStore\src\main\resources);
5.	Теперь в правой строне IDEA найдите вкладку для настройки базы данных Database и добавьте новый Mysql Data Source с помощью кнопки +:
6.	В открывшемся окне заполните поля name, host, user, password, database. После этого нажмите кнопку Test Connection, и если соединение прошло успешно, появится зеленая галочка, а затем можно нажимать на ok:
7.	Проект готов к запуску.
