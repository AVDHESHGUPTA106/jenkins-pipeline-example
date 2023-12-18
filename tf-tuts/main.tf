resource "aws_instance" "my_vm" {
  ami           = var.ami //Ubuntu AMI
  instance_type = var.instance_type

  tags = {
    Name = var.name_tag,
  }
}

# resource "aws_timestreamwrite_database" "anaplan-timestream-db" {
#   database_name = "tsdb-model-db"
# }

# resource "aws_timestreamwrite_table" "timestream-tbl" {
#   database_name = aws_timestreamwrite_database.anaplan-timestream-db.database_name
#   table_name    = "tsdb-model-table"
#   retention_properties {
#     magnetic_store_retention_period_in_days = 1
#     memory_store_retention_period_in_hours  = 1
#   }
# }

data "aws_region" "current" {}

# data "aws_timestreamwrite_database" "timestream_db"{}

