using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

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
                    Nombres = table.Column<string>(type: "TEXT", unicode: false, maxLength: 100, nullable: true),
                    Clave = table.Column<string>(type: "TEXT", nullable: true),
                    ConfiguracionId = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK__Clientes__71ABD087B375C419", x => x.ClienteId);
                });

            migrationBuilder.CreateTable(
                name: "Configuraciones",
                columns: table => new
                {
                    ConfiguracionId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Theme = table.Column<int>(type: "INTEGER", nullable: false),
                    ColorSchemeIndex = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Configuraciones", x => x.ConfiguracionId);
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
                    FechaCreacion = table.Column<DateTime>(type: "datetime", nullable: true),
                    ClienteId = table.Column<int>(type: "INTEGER", nullable: true),
                    SistemaId = table.Column<int>(type: "INTEGER", nullable: true),
                    TipoId = table.Column<int>(type: "INTEGER", nullable: true),
                    PrioridadId = table.Column<int>(type: "INTEGER", nullable: true),
                    EstatusId = table.Column<int>(type: "INTEGER", nullable: true),
                    Especificaciones = table.Column<string>(type: "TEXT", unicode: false, nullable: true),
                    FechaFinalizado = table.Column<DateTime>(type: "datetime", nullable: true),
                    Orden = table.Column<int>(type: "INTEGER", nullable: true),
                    ClientesClienteId = table.Column<int>(type: "INTEGER", nullable: true),
                    PrioridadesPrioridadId = table.Column<int>(type: "INTEGER", nullable: true),
                    SistemasSistemaId = table.Column<int>(type: "INTEGER", nullable: true),
                    TiposTipoId = table.Column<int>(type: "INTEGER", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK__eTicket__712CC6070A90FFD9", x => x.TicketId);
                    table.ForeignKey(
                        name: "FK_eTicket_Clientes_ClientesClienteId",
                        column: x => x.ClientesClienteId,
                        principalTable: "Clientes",
                        principalColumn: "ClienteId");
                    table.ForeignKey(
                        name: "FK_eTicket_Estatus_EstatusId",
                        column: x => x.EstatusId,
                        principalTable: "Estatus",
                        principalColumn: "EstatusId");
                    table.ForeignKey(
                        name: "FK_eTicket_Prioridades_PrioridadesPrioridadId",
                        column: x => x.PrioridadesPrioridadId,
                        principalTable: "Prioridades",
                        principalColumn: "PrioridadId");
                    table.ForeignKey(
                        name: "FK_eTicket_Sistemas_SistemasSistemaId",
                        column: x => x.SistemasSistemaId,
                        principalTable: "Sistemas",
                        principalColumn: "SistemaId");
                    table.ForeignKey(
                        name: "FK_eTicket_Tipos_TiposTipoId",
                        column: x => x.TiposTipoId,
                        principalTable: "Tipos",
                        principalColumn: "TipoId");
                });

            migrationBuilder.CreateTable(
                name: "Respuestas",
                columns: table => new
                {
                    RespuestaId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Respuesta = table.Column<string>(type: "TEXT", nullable: true),
                    ClienteId = table.Column<int>(type: "INTEGER", nullable: true),
                    Fecha = table.Column<DateTime>(type: "TEXT", nullable: true),
                    TicketId = table.Column<int>(type: "INTEGER", nullable: true),
                    ClientesClienteId = table.Column<int>(type: "INTEGER", nullable: true),
                    TicketsTicketId = table.Column<int>(type: "INTEGER", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Respuestas", x => x.RespuestaId);
                    table.ForeignKey(
                        name: "FK_Respuestas_Clientes_ClientesClienteId",
                        column: x => x.ClientesClienteId,
                        principalTable: "Clientes",
                        principalColumn: "ClienteId");
                    table.ForeignKey(
                        name: "FK_Respuestas_eTicket_TicketsTicketId",
                        column: x => x.TicketsTicketId,
                        principalTable: "eTicket",
                        principalColumn: "TicketId");
                });

            migrationBuilder.InsertData(
                table: "Clientes",
                columns: new[] { "ClienteId", "Clave", "ConfiguracionId", "Nombres" },
                values: new object[,]
                {
                    { 1, "Samuel23", 13, "Samuel" },
                    { 2, "Rafa23", 13, "Rafa" },
                    { 3, "Jeison23", 13, "Jeison" },
                    { 4, "Enel23", 13, "Enel" }
                });

            migrationBuilder.InsertData(
                table: "Configuraciones",
                columns: new[] { "ConfiguracionId", "ColorSchemeIndex", "Theme" },
                values: new object[,]
                {
                    { 1, 0, 0 },
                    { 2, 1, 0 },
                    { 3, 2, 0 },
                    { 4, 3, 0 },
                    { 5, 4, 0 },
                    { 6, 5, 0 },
                    { 7, 0, 1 },
                    { 8, 1, 1 },
                    { 9, 2, 1 },
                    { 10, 3, 1 },
                    { 11, 4, 1 },
                    { 12, 5, 1 },
                    { 13, 0, 2 },
                    { 14, 1, 2 },
                    { 15, 2, 2 },
                    { 16, 3, 2 },
                    { 17, 4, 2 },
                    { 18, 5, 2 }
                });

            migrationBuilder.InsertData(
                table: "Estatus",
                columns: new[] { "EstatusId", "Estatus" },
                values: new object[,]
                {
                    { 1, "Nuevo" },
                    { 2, "En proceso" },
                    { 3, "Cerrado" }
                });

            migrationBuilder.InsertData(
                table: "Prioridades",
                columns: new[] { "PrioridadId", "Prioridad" },
                values: new object[,]
                {
                    { 1, "Baja" },
                    { 2, "Normal" },
                    { 3, "Media" },
                    { 4, "Alta" },
                    { 5, "Emergencia" }
                });

            migrationBuilder.InsertData(
                table: "Sistemas",
                columns: new[] { "SistemaId", "Sistema" },
                values: new object[,]
                {
                    { 1, "Ventas SS" },
                    { 2, "SS Office" },
                    { 3, "SS Rent Reporter" }
                });

            migrationBuilder.InsertData(
                table: "Tipos",
                columns: new[] { "TipoId", "Tipo" },
                values: new object[,]
                {
                    { 1, "Problemas" },
                    { 2, "Sugerencia" },
                    { 3, "Soporte Directo" },
                    { 4, "Otros" }
                });

            migrationBuilder.CreateIndex(
                name: "IX_eTicket_ClientesClienteId",
                table: "eTicket",
                column: "ClientesClienteId");

            migrationBuilder.CreateIndex(
                name: "IX_eTicket_EstatusId",
                table: "eTicket",
                column: "EstatusId");

            migrationBuilder.CreateIndex(
                name: "IX_eTicket_PrioridadesPrioridadId",
                table: "eTicket",
                column: "PrioridadesPrioridadId");

            migrationBuilder.CreateIndex(
                name: "IX_eTicket_SistemasSistemaId",
                table: "eTicket",
                column: "SistemasSistemaId");

            migrationBuilder.CreateIndex(
                name: "IX_eTicket_TiposTipoId",
                table: "eTicket",
                column: "TiposTipoId");

            migrationBuilder.CreateIndex(
                name: "IX_Respuestas_ClientesClienteId",
                table: "Respuestas",
                column: "ClientesClienteId");

            migrationBuilder.CreateIndex(
                name: "IX_Respuestas_TicketsTicketId",
                table: "Respuestas",
                column: "TicketsTicketId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Configuraciones");

            migrationBuilder.DropTable(
                name: "Respuestas");

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
