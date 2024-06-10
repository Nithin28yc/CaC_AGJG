output "aws_key_pair" {
   value = aws_key_pair.auth.key_name
}
output "vpc_id" {
   value = aws_vpc.main.id
}
output "aws_subnet_id" {
   value = aws_subnet.public-subnet.id
}
output "aws_instance_id" {
   value = aws_instance.E2cInstance.id
}

output "aws_route_table_id" {
   value = aws_route_table.route_table_public.id
}

output "aws_internet_gateway_id" {
   value = aws_internet_gateway.igw.id
}

output "aws_security_group_id" {
   value = aws_security_group.allow_tls.id
}

#output "aws_s3_id" {
#   value = aws_s3_bucket.ec2instance.id
#}
