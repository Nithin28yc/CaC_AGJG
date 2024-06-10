provider "google" {
  project = "acn-devopsgcp"
  credentials = var.jsonkey
  region = var.region

}
variable "client" {
    default = "terraform-gcp"
}
/*variable "credentials" {
default="###gcpkey###"
}*/
variable "jsonkey" {
default=<<EOF
###gcpkey###
EOF
}
variable "cidr_block" {
  default = "10.2.0.0/16"
}
variable "region" {
  default = "us-central1"
}
variable "ip_range" {
  default = "0.0.0.0/0"
}
variable "ingress" {
  default = "INGRESS"
}
variable "egress" {
  default = "EGRESS"
}