#########################################################################################
#                       The resources created                                           #
#  resource group,virtual network,subnets,route tables,security groups,public ip,       #
#                     network interface, virtual machines, sshkey gen                   #
#########################################################################################


resource "azurerm_resource_group" "example" {
  name     = "IAC_Terraform"
  location = "Southeast Asia"
}

resource "azurerm_virtual_network" "example" {
  name                = "virtualNetwork1"
  location            = "${azurerm_resource_group.example.location}"
  resource_group_name = "${azurerm_resource_group.example.name}"
  address_space       = ["10.0.0.0/16"]
}

resource "azurerm_subnet" "example" {
  name                 = "subnet1"
  resource_group_name  = "${azurerm_resource_group.example.name}"
  virtual_network_name = "${azurerm_virtual_network.example.name}"
  address_prefixes     = ["10.0.1.0/24"]
}

resource "azurerm_route_table" "example" {
  name = "myRouteTable"
  location = "${azurerm_resource_group.example.location}"
  resource_group_name = "${azurerm_resource_group.example.name}"
  disable_bgp_route_propagation = false
    route {
    name           = "route1"
    address_prefix = "10.1.0.0/16"
    next_hop_type  = "vnetlocal"
  }
}

resource "azurerm_subnet_route_table_association" "example" {
  subnet_id      = "${azurerm_subnet.example.id}"
  route_table_id = "${azurerm_route_table.example.id}"
}

resource "azurerm_network_security_group" "example" {
  name                = "Terra_SecGrp"
  resource_group_name = "${azurerm_resource_group.example.name}"
  location            = "${azurerm_resource_group.example.location}"

  security_rule {
    name                       = "test123"
    priority                   = 100
    direction                  = "Inbound"
    access                     = "Allow"
    protocol                   = "Tcp"
    source_port_range          = "*"
    destination_port_range     = "22"
    source_address_prefix      = "106.206.104.4/32"
    destination_address_prefix = "*"
  }
}

resource "azurerm_subnet_network_security_group_association" "example" {
  subnet_id                 = "${azurerm_subnet.example.id}"
  network_security_group_id = "${azurerm_network_security_group.example.id}"
}

resource "azurerm_public_ip" "example" {
  name                = "Public-IP"
  location            = "${azurerm_resource_group.example.location}"
  resource_group_name = "${azurerm_resource_group.example.name}"
  allocation_method   = "Static"
  sku                 = "Standard"
}

resource "azurerm_network_interface" "example" {
  name                = "NetworkInterface"
  location            = "${azurerm_resource_group.example.location}"
  resource_group_name = "${azurerm_resource_group.example.name}"

  ip_configuration {
    name                          = "testconfiguration1"
    subnet_id                     = "${azurerm_subnet.example.id}"
    private_ip_address_allocation = "Dynamic"
    public_ip_address_id = azurerm_public_ip.example.id
  }
}

resource "azurerm_network_interface_security_group_association" "name" {
  network_interface_id      = "${azurerm_network_interface.example.id}"
  network_security_group_id = "${azurerm_network_security_group.example.id}"
}

provider "tls"  {}
resource "tls_private_key" "tlsauth" {
  algorithm = "RSA"
  rsa_bits = 4096
}

provider "local" {}
resource "local_file" "Key" {
  content = "${tls_private_key.tlsauth.private_key_pem}"
  filename = "./auth.pem"
}

resource "azurerm_linux_virtual_machine" "myterraformvm" {
  name                  = "IAC-VM"
  location              = "${azurerm_resource_group.example.location}"
  resource_group_name   = "${azurerm_resource_group.example.name}"
  network_interface_ids = [azurerm_network_interface.example.id]
  size               = "Standard_DS1_v2"

    os_disk {
        name              = "myOsDisk"
        caching           = "ReadWrite"
        storage_account_type = "Premium_LRS"
    }

    source_image_reference {
        publisher = "Canonical"
        offer     = "UbuntuServer"
        sku       = "18.04-LTS"
        version   = "latest"
    }

    computer_name  = "myvm"
    admin_username = "azureuser"
    disable_password_authentication = true

    admin_ssh_key {
        username       = "azureuser"
      #  public_key     = "${azurerm_ssh_public_key.example.name}"
	public_key     = tls_private_key.tlsauth.public_key_openssh
    }

}