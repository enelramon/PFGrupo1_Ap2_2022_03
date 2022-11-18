﻿using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Ticket.Api.Migrations
{
    /// <inheritdoc />
    public partial class inicial : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Clientes",
                columns: table => new
                {
                    ClienteId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Nombres = table.Column<string>(type: "TEXT", unicode: false, maxLength: 100, nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK__Clientes__71ABD087B375C419", x => x.ClienteId);
                });

            migrationBuilder.CreateTable(
                name: "Estatus",
                columns: table => new
                {
                    EstatusId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Estatus = table.Column<string>(type: "TEXT", unicode: false, maxLength: 50, nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK__Estatus__DE10F58DA375F6EC", x => x.EstatusId);
                });

            migrationBuilder.CreateTable(
                name: "Prioridades",
                columns: table => new
                {
                    PrioridadId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Prioridad = table.Column<string>(type: "TEXT", unicode: false, maxLength: 50, nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK__Priorida__3939172E0518F8F3", x => x.PrioridadId);
                });

            migrationBuilder.CreateTable(
                name: "Sistemas",
                columns: table => new
                {
                    SistemaId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Sistema = table.Column<string>(type: "TEXT", unicode: false, maxLength: 50, nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK__Sistemas__4C36BB868F17E7A9", x => x.SistemaId);
                });

            migrationBuilder.CreateTable(
                name: "Tipos",
                columns: table => new
                {
                    TipoId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Tipo = table.Column<string>(type: "TEXT", unicode: false, maxLength: 50, nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK__Tipos__97099EB7224F755C", x => x.TipoId);
                });

            migrationBuilder.CreateTable(
                name: "eTicket",
                columns: table => new
                {
                    TicketId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Fecha = table.Column<DateTime>(type: "datetime", nullable: true),
                    ClienteId = table.Column<int>(type: "INTEGER", nullable: true),
                    SistemaId = table.Column<int>(type: "INTEGER", nullable: true),
                    TipoId = table.Column<int>(type: "INTEGER", nullable: true),
                    PrioridadId = table.Column<int>(type: "INTEGER", nullable: true),
                    EstatusId = table.Column<int>(type: "INTEGER", nullable: true),
                    Especificaciones = table.Column<string>(type: "TEXT", unicode: false, nullable: true),
                    FechaFinalizado = table.Column<DateTime>(type: "datetime", nullable: true),
                    Orden = table.Column<int>(type: "INTEGER", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK__eTicket__712CC6070A90FFD9", x => x.TicketId);
                    table.ForeignKey(
                        name: "FK__eTicket__Cliente__1CF15040",
                        column: x => x.ClienteId,
                        principalTable: "Clientes",
                        principalColumn: "ClienteId");
                    table.ForeignKey(
                        name: "FK__eTicket__Estatus__20C1E124",
                        column: x => x.EstatusId,
                        principalTable: "Estatus",
                        principalColumn: "EstatusId");
                    table.ForeignKey(
                        name: "FK__eTicket__Priorid__1FCDBCEB",
                        column: x => x.PrioridadId,
                        principalTable: "Prioridades",
                        principalColumn: "PrioridadId");
                    table.ForeignKey(
                        name: "FK__eTicket__Sistema__1DE57479",
                        column: x => x.SistemaId,
                        principalTable: "Sistemas",
                        principalColumn: "SistemaId");
                    table.ForeignKey(
                        name: "FK__eTicket__TipoId__1ED998B2",
                        column: x => x.TipoId,
                        principalTable: "Tipos",
                        principalColumn: "TipoId");
                });

            migrationBuilder.CreateIndex(
                name: "IX_eTicket_ClienteId",
                table: "eTicket",
                column: "ClienteId");

            migrationBuilder.CreateIndex(
                name: "IX_eTicket_EstatusId",
                table: "eTicket",
                column: "EstatusId");

            migrationBuilder.CreateIndex(
                name: "IX_eTicket_PrioridadId",
                table: "eTicket",
                column: "PrioridadId");

            migrationBuilder.CreateIndex(
                name: "IX_eTicket_SistemaId",
                table: "eTicket",
                column: "SistemaId");

            migrationBuilder.CreateIndex(
                name: "IX_eTicket_TipoId",
                table: "eTicket",
                column: "TipoId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "eTicket");

            migrationBuilder.DropTable(
                name: "Clientes");

            migrationBuilder.DropTable(
                name: "Estatus");

            migrationBuilder.DropTable(
                name: "Prioridades");

            migrationBuilder.DropTable(
                name: "Sistemas");

            migrationBuilder.DropTable(
                name: "Tipos");
        }
    }
}