-- drop database announcement;
use announcement;


select * from category;
select * from announcement;
SELECT * FROM announcement.user;

insert into category (categoryID, categoryName) value 
(1, "ทั่วไป"),
(2, "ทุนการศึกษา"),
(3, "หางาน"),
(4, "ฝึกงาน");

insert into announcement (announcementID, announcementTitle, announcementDescription, announcementCategory, publishDate, closeDate, announcementDisplay) value 
(1, 'บริษัท เน็ตเซอร์พลัส จำกัด รับสมัครงาน 2 ตำแหน่ง', 'บริษัท เน็ตเซอร์พลัส จำกัด เปิดรับสมัครงาน 2 ตำแหน่ง Application Support และ Customer Support', 3, null, null, 'N'),
(2, 'รายชื่อนักศึกษาที่ได้รับทุนการศึกษาประเภท "ทุนจ้างงาน" 2/2565', 'คณะ ฯ ประกาศรายชื่อนักศึกษาที่ได้รับทุนการศึกษาประเภท "ทุนจ้างงาน" ประจําภาคการศึกษา 2/2565', 2, null, '2023-05-31 11:00:00', 'Y'),
(3, 'แนวปฎิบัติการสอบออนไลน์ พ.ศ. 2565', 'ประกาศมหาวิทยาลัยเทคโนโลยี พระจอมเกล้าธนบุรี เรื่องแนวทางปฎิบัติการสอบออนไลน์ พ.ศ. 2565', 1, '2023-01-26 23:00:00', null, 'Y'),
(4, 'กิจกรรมพี่อ้อย พี่ฉอด On Tour 2566', 'ขอเชิญนักศึกษาทุกชั้นปี เข้าร่วมกิจกรรมพี่อ้อย พี่ฉอด On Tour', 1, '2023-04-18 23:00:00', '2023-05-08 11:00:00', 'Y');

insert into user (id, username,password, name, email, role, createdOn,updatedOn) value 
(1,"sanit","$argon2i$v=19$m=60000,t=1,p=1$WjE4c2RQZDlvQWJzWmc5aA$6RJhgyTVjPio9MHn9aKDgRvHyWM14hiuIN4flDs3Opk","Sanit Sirisawatvatana","sanit.sir@kmutt.ac.th","admin","2023-08-15 08:00:00+07:00","2023-08-15 08:00:00+07:00"),
(2,"pornthip","$argon2i$v=19$m=60000,t=1,p=1$WjE4c2RQZDlvQWJzWmc5aA$yLU1VqUdt7I9jD3Cq/brOUX+MtMzk+I7NxB/+8usxxI","Pornthip Sirijutikul","pornthip.sri@kmutt.ac.th","announcer","2023-08-15 09:30:00+07:00","2023-08-15 09:30:00+07:00"),
(3,"jaruwan_w","$argon2i$v=19$m=60000,t=1,p=1$WjE4c2RQZDlvQWJzWmc5aA$HDF2pzeLpk/H89RuWP3fLaq65FasZuHtt1Kt9sqqc0U","Jaruwan Maneesart","jaruwan.wee@kmutt.ac.th","announcer","2023-08-16 08:00:00+07:00","2023-08-16 08:00:00+07:00"),
(4,"vichchuda","$argon2i$v=19$m=60000,t=1,p=1$WjE4c2RQZDlvQWJzWmc5aA$xKYMrxtFHO6lrbOc0rYcpUnEpgOWmh/MgqVYXuILdb0","Vichchuda Tedoloh","vichchuda.ted@kmutt.ac.th","announcer","2023-08-16 09:30:00+07:00","2023-08-16 09:30:00+07:00");


