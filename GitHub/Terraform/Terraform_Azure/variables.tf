provider "azurerm" {
  features {}

  tenant_id = var.tenant_id
  subscription_id = var.subscription_id
  client_id = var.client_id
  client_secret = var.client_secret
}

variable "tenant_id" {
default = "3946245e-d486-44ff-8819-16cf8d49689b"
}

variable "subscription_id" {
default = "d38b4b1a-7e54-42bc-a880-0eb6ab89bb04"
}

variable "client_id" {
default = "89181367-b7f3-4ba0-b8c3-555c092a6f63"
}

variable "client_secret" {
default = "G1_s9zoS1oE2XKQKus-m3_2_pN...ZHbk_"
}