#########################################################################################
#                       The resources created                                           #
#        SSH Key, Compute Network, Compute Subnetwork,  Compute Router                  #
#                         Compute Firewall, Compute Instance                            #
#########################################################################################
provider "tls" {}
resource "tls_private_key" "ssh" {
  algorithm = "RSA"
  rsa_bits  = "4096"
}
provider "local" {}
resource "local_file" "private_key" {
  content         = "${tls_private_key.ssh.private_key_pem}"
  filename        = "./gcp.pem"
}
resource "google_compute_instance" "my_dummy_vm" {
  name = "${var.client}-vm"
  machine_type = "f1-micro"
  zone         = "${var.region}-a"
  boot_disk {
    initialize_params {
      image = "ubuntu-os-cloud/ubuntu-1804-lts"
    }
  }
  metadata = {
    ssh-keys = "ubuntu:${tls_private_key.ssh.public_key_openssh}"
  }
  network_interface {
    network = google_compute_network.my_dummy_vpc.id
    subnetwork = google_compute_subnetwork.my_dummy_subnet.id
    access_config {}
  }
}
resource "google_compute_network" "my_dummy_vpc" {
  name = "${var.client}-vpc"
  auto_create_subnetworks = false
}
resource "google_compute_subnetwork" "my_dummy_subnet" {
  name          = "${var.client}-subnet"
  ip_cidr_range = "${var.cidr_block}"
  region        = "${var.region}"
  network       = google_compute_network.my_dummy_vpc.id
}
resource "google_compute_router" "my_dummy_router" {
  name = "${var.client}-router"
  network = google_compute_network.my_dummy_vpc.id
}
/*resource "google_compute_address" "static_ip" {
  name    = "${var.client}-staticip"
}
resource "google_compute_router_nat" "my_dummy_nat" {
   name = "${var.client}-nat"
   router = google_compute_router.my_dummy_router.name
   nat_ip_allocate_option             = "MANUAL_ONLY"
   source_subnetwork_ip_ranges_to_nat = "ALL_SUBNETWORKS_ALL_IP_RANGES"
   nat_ips                            = [google_compute_address.static_ip.self_link]
   depends_on = [google_compute_subnetwork.my_dummy_subnet]
}*/
resource "google_compute_firewall" "my_dummy_afri" {
    name    = "${var.client}-firewall-ingress-allow"
    network =  google_compute_network.my_dummy_vpc.name
    priority = 20
	direction = "${var.ingress}"
    allow {
      protocol = "tcp"
		ports    = ["22"]
    }
    source_ranges = ["103.236.193.90/32"]
}
resource "google_compute_firewall" "my_dummy_afre" {
    name    = "${var.client}-firewall-egress-allow"
    network =  google_compute_network.my_dummy_vpc.name
    priority = 22
	direction = "${var.egress}"
    allow {
      protocol = "tcp"
		ports    = ["80"]
    }
    destination_ranges = ["${var.ip_range}"]

}
resource "google_compute_firewall" "my_dummy_dfre" {
    name    = "${var.client}-firewall-egress-deny"
    network =  google_compute_network.my_dummy_vpc.name
    priority = 65000
	direction = "${var.egress}"
    deny {
      protocol = "all"
    }
    destination_ranges = ["${var.ip_range}"]
}

resource "google_compute_firewall" "my_dummy_dfri" {
    name    = "${var.client}-firewall-ingress-deny"
    network =  google_compute_network.my_dummy_vpc.name
    priority = 65000
	direction = "${var.ingress}"
    deny {
      protocol = "all"
    }
    source_ranges = ["${var.ip_range}"]
}