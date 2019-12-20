create table produtos
(
	id int auto_increment,
	nome text not null,
	quantidade int not null,
	constraint produtos_pk
		primary key (id)
);

create table vendas
(
	id int auto_increment,
	id_produto int not null,
	quantidade int not null,
	data_venda timestamp default NOW() null,
	constraint vendas_pk
		primary key (id),
	constraint vendas_produtos_id_fk
		foreign key (id_produto) references produtos (id)
)
comment 'registro das vendas do produto.';

