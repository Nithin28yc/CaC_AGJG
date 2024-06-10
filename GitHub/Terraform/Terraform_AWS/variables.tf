provider "aws" {
 region = var.region
}
variable "vpc_description" {
 default ="outbound vpc"
}
variable "vpc_cidr_block" {
 default= "11.0.0.0/16"
}
variable "region"{
 default= "us-east-2"
}
variable "availability_zone_1"{
 default= "us-east-2a"
}
variable "client" {
 default = "testing"
}
