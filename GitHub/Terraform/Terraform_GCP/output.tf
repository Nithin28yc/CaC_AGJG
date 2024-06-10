output "compute_network_id" {
  value = google_compute_network.my_dummy_vpc.id
}
output "compute_subnetwork_id" {
  value = google_compute_subnetwork.my_dummy_subnet.id
}
output "compute_router_id" {
  value = google_compute_router.my_dummy_router.id
}
output "compute_firewall_ingress_allow" {
  value = google_compute_firewall.my_dummy_afri.id
}
output "compute_firewall_ingress_deny" {
  value = google_compute_firewall.my_dummy_dfri.id
}
output "compute_firewall_egress_allow" {
  value = google_compute_firewall.my_dummy_afre.id
}
output "compute_firewall_egress_deny" {
  value = google_compute_firewall.my_dummy_dfre.id
}
output "compute_instance_id" {
  value = google_compute_instance.my_dummy_vm.instance_id
}