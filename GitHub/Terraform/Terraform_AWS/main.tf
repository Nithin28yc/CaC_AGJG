#########################################################################################
#                       The resources created                                           #
#  VPC,Subnet,Key pair,Internet gateways and associations,security groups, route tables #
#                             ec2 instance                                              #
#########################################################################################

resource "aws_key_pair" "auth" {
  key_name   = "auth-k"
  public_key = "${tls_private_key.tlsauth.public_key_openssh}"
}
provider "tls" {}
resource "tls_private_key" "tlsauth" {
  algorithm = "RSA"
}
provider "local" {}
resource "local_file" "Key" {
  content = "${tls_private_key.tlsauth.private_key_pem}"
  filename = "./auth.pem"
}

resource "aws_vpc" "main" {
  cidr_block = var.vpc_cidr_block
  instance_tenancy = "default"
  tags = {
    Name = "${var.client}-Main VPC"
  }
}
resource "aws_subnet" "public-subnet" {
  vpc_id = aws_vpc.main.id
  cidr_block = "11.0.10.0/25"
  availability_zone = "${var.availability_zone_1}"
  tags = {
    Name="${var.client}-demo-subnet-public"
  }
}
resource "aws_route_table" "route_table_public" {
  vpc_id     = aws_vpc.main.id
  tags = {
    Name = "${var.client}-public-route"
  }
}
resource "aws_route_table_association" "route_association_public" {
  subnet_id = aws_subnet.public-subnet.id
  route_table_id = aws_route_table.route_table_public.id
}
resource "aws_main_route_table_association" "route-main" {
  vpc_id         = aws_vpc.main.id
  route_table_id = aws_route_table.route_table_public.id
}
resource "aws_internet_gateway" "igw" {
  vpc_id = aws_vpc.main.id
  tags = {
    Name="${var.client}-demo-gateway"
    }
}
resource "aws_route" "igw_association_route_table_public" {
  route_table_id         = aws_route_table.route_table_public.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.igw.id
  depends_on             = [aws_route_table.route_table_public]
}
resource "aws_security_group" "allow_tls" {
  name        = "allow_tls"
  description = "Allow TLS inbound traffic"
  vpc_id      = aws_vpc.main.id

  ingress {
      description = "my ip"
      from_port        = 22
      to_port          = 22
      protocol         = "tcp"
      cidr_blocks      = ["49.206.34.212/32"]
  }

  egress {
      description      = var.vpc_description
      from_port        = 8080
      to_port          = 8080
      protocol         = "tcp"
      cidr_blocks      = ["0.0.0.0/0"]
      ipv6_cidr_blocks = ["::/0"]
    }
  egress {
      description      = var.vpc_description
      from_port        = 443
      to_port          = 443
      protocol         = "tcp"
      cidr_blocks      = ["0.0.0.0/0"]
      ipv6_cidr_blocks = ["::/0"]
    }
  egress {
      description      = var.vpc_description
      from_port        = 80
      to_port          = 80
      protocol         = "tcp"
      cidr_blocks      = ["0.0.0.0/0"]
      ipv6_cidr_blocks = ["::/0"]
    }
  tags = {
    Name = "${var.client}-allow_tls"
  }
}
resource "aws_instance" "E2cInstance" {
  ami = "ami-00399ec92321828f5"
  instance_type = "t2.micro"
  key_name = aws_key_pair.auth.key_name
  subnet_id = aws_subnet.public-subnet.id
  security_groups = [aws_security_group.allow_tls.id]
  associate_public_ip_address = true
  tags = {
    Name = "${var.client}-HelloWorld"
  }
}
/*resource "aws_s3_bucket" "ec2instance" {
  bucket = "pairingbucker1234567"
  acl    = "private"
  tags = {
    Name        = "newarea1"
    Environment = "Dev"
  }
}*/