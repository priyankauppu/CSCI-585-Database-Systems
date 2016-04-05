/*Q1*/
select count(name) as number_of_users_born,strftime('%Y',dob) as year from users where strftime('%Y',dob)>='1970' group by strftime('%Y',dob);
/*Q2*/
SELECT strftime('%H',post_date) as HOUR,count(ad_id) as number_of_ads_posted from ads group by strftime('%H',post_date) order by count(ad_id) desc limit 1;
/*Q3*/
select count(ad_id) from ads where category_id=250 and post_date>(select last_logout from users where username="lhartj");
/*Q4*/
select cities.name from cities,regions where cities.city_id=regions.city_id group by cities.city_id order by count(cities.city_id) desc limit 1;
/*Q5*/
select name from users inner join ads on users.username=ads.creator where ad_id=(select ad_id from likes group by ad_id order by count(username) desc limit 1);
/*Q6*/
select name as Region_Name from regions where region_id= (select region_id from ads group by region_id order by count(ad_id) desc limit 1);
/*Q7*/
select name from users inner join ads on users.username=ads.creator where strftime('%Y',post_date)>='2015' group by creator  order by count(ad_id) desc limit 3; 
/*Q8*/
select title,price from ads where creator="bnguyen50" order by post_date desc limit 1;

/*
Sree Priyanka Uppu
6822468564
*/
