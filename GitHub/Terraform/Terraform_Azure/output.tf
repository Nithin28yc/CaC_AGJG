output "azurerm_resource_group" {
  description = "RG name"
  value = [azurerm_resource_group.example.name]
}

output "azurerm_virtual_network" {
  description = "Virtual network name"
  value = [azurerm_virtual_network.example.name,azurerm_virtual_network.example.id]
}

output "azurerm_subnet" {
  value = [azurerm_subnet.example.name,azurerm_subnet.example.id,azurerm_subnet.example.address_prefixes]
}

output "azurerm_route_table" {
  value = [azurerm_route_table.example.name,azurerm_route_table.example.id]
}

output "azurerm_network_security_group" {
  value = [azurerm_network_security_group.example.name,azurerm_network_security_group.example.id]
}

output "azurerm_public_ip" {
  value = [azurerm_public_ip.example.id]
}

output "azurerm_linux_virtual_machine" {
  value = [azurerm_linux_virtual_machine.myterraformvm.id,azurerm_linux_virtual_machine.myterraformvm.network_interface_ids]
}