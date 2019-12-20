update produtos set quantidade = 
(select quantidade from produtos where id = 1) - 1
where id = 1
