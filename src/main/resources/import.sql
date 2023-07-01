INSERT INTO `pizzas`(`id`, `price`, `description`, `name`, `photo`) VALUES (1,7,"Pomodoro, mozzarella, basilico","Margherita","https://www.finedininglovers.it/sites/g/files/xknfdk1106/files/styles/recipes_1200_800_fallback/public/fdl_content_import_it/margherita-50kalo.jpg?itok=v9nHxNMS");
INSERT INTO `pizzas`(`id`, `price`, `description`, `name`, `photo`) VALUES (2,8,"Pomodoro, mozzarella, funghi","Funghi","https://www.italianstylecooking.net/wp-content/uploads/2022/01/Pizza-Funghi.jpg");
INSERT INTO `pizzas`(`id`, `price`, `description`, `name`, `photo`) VALUES (3,8,"Pomodoro, mozzarella, salamino piccante","Diavola","https://i1.wp.com/www.piccolericette.net/piccolericette/wp-content/uploads/2017/06/3236_Pizza.jpg?resize=895%2C616&ssl=1");
INSERT INTO `pizzas`(`id`, `price`, `description`, `name`, `photo`) VALUES (4,9,"Pomodoro, mozzarella, acciughe,capperi,olive","Siciliana","https://gdsit.cdn-immedia.net/2014/10/Pizza.jpg");
INSERT INTO `ingridients` (`id`, `name`) VALUES (NULL, 'pomodoro'), (NULL, 'mozzarella'), (NULL, 'funghi'), (NULL, 'acciughe');
INSERT INTO `pizza_ingridient` (`ingridient_id`, `pizza_id`) VALUES ('1', '1');
INSERT INTO `pizza_ingridient` (`ingridient_id`, `pizza_id`) VALUES ('1', '2');
INSERT INTO `pizza_ingridient` (`ingridient_id`, `pizza_id`) VALUES ('2', '1');
INSERT INTO `pizza_ingridient` (`ingridient_id`, `pizza_id`) VALUES ('3', '1');