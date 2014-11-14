-- Add additional columns to support profile changes
alter table User add column image_id varchar(100), add column additional_info varchar(10000);
update User set image_id='', additional_info='';
