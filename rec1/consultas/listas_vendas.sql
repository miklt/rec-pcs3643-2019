select nome, quantidade, data_venda from vendas
inner join produtos on
vendas.id_produto = produtos.id