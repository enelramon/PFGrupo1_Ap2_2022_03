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
                    Estatus = table.Column<string>(type: "TEXT", unicode: false, maxLength: 50, nullable: false)
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
                    Sistema = table.Column<string>(type: "TEXT", unicode: false, maxLength: 50, nullable: false)
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
                name: "Clientes",
                columns: table => new
                {
                    ClienteId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Nombres = table.Column<string>(type: "TEXT", unicode: false, maxLength: 100, nullable: false),
                    Clave = table.Column<string>(type: "TEXT", nullable: false),
                    ConfiguracionId = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK__Clientes__71ABD087B375C419", x => x.ClienteId);
                    table.ForeignKey(
                        name: "FK_Clientes_Configuraciones_ConfiguracionId",
                        column: x => x.ConfiguracionId,
                        principalTable: "Configuraciones",
                        principalColumn: "ConfiguracionId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Tickets",
                columns: table => new
                {
                    TicketId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    FechaCreacion = table.Column<DateTime>(type: "datetime", nullable: false),
                    FechaModificacion = table.Column<DateTime>(type: "TEXT", nullable: true),
                    ClienteId = table.Column<int>(type: "INTEGER", nullable: false),
                    SistemaId = table.Column<int>(type: "INTEGER", nullable: false),
                    TipoId = table.Column<int>(type: "INTEGER", nullable: false),
                    PrioridadId = table.Column<int>(type: "INTEGER", nullable: false),
                    EstatusId = table.Column<int>(type: "INTEGER", nullable: false),
                    Especificaciones = table.Column<string>(type: "TEXT", unicode: false, nullable: false),
                    FechaFinalizado = table.Column<DateTime>(type: "datetime", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK__Tickets__712CC6070A90FFD9", x => x.TicketId);
                    table.ForeignKey(
                        name: "FK_Tickets_Clientes_ClienteId",
                        column: x => x.ClienteId,
                        principalTable: "Clientes",
                        principalColumn: "ClienteId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Tickets_Estatus_EstatusId",
                        column: x => x.EstatusId,
                        principalTable: "Estatus",
                        principalColumn: "EstatusId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Tickets_Prioridades_PrioridadId",
                        column: x => x.PrioridadId,
                        principalTable: "Prioridades",
                        principalColumn: "PrioridadId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Tickets_Sistemas_SistemaId",
                        column: x => x.SistemaId,
                        principalTable: "Sistemas",
                        principalColumn: "SistemaId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Tickets_Tipos_TipoId",
                        column: x => x.TipoId,
                        principalTable: "Tipos",
                        principalColumn: "TipoId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Respuestas",
                columns: table => new
                {
                    RespuestaId = table.Column<int>(type: "INTEGER", nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Contenido = table.Column<string>(type: "TEXT", nullable: false),
                    ClienteId = table.Column<int>(type: "INTEGER", nullable: false),
                    Fecha = table.Column<DateTime>(type: "TEXT", nullable: false),
                    TicketId = table.Column<int>(type: "INTEGER", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Respuestas", x => x.RespuestaId);
                    table.ForeignKey(
                        name: "FK_Respuestas_Clientes_ClienteId",
                        column: x => x.ClienteId,
                        principalTable: "Clientes",
                        principalColumn: "ClienteId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Respuestas_Tickets_TicketId",
                        column: x => x.TicketId,
                        principalTable: "Tickets",
                        principalColumn: "TicketId",
                        onDelete: ReferentialAction.Cascade);
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
                    { 1, "Auto System Gym" },
                    { 2, "Auto System Office" },
                    { 3, "Auto System Finanzas" },
                    { 4, "Auto System Ventas" },
                    { 5, "Auto System Produccion" },
                    { 6, "Auto System Analyzer" },
                    { 7, "Auto System Dealer" },
                    { 8, "Auto System Education" },
                    { 9, "Auto System Restaurant" }
                });

            migrationBuilder.InsertData(
                table: "Tipos",
                columns: new[] { "TipoId", "Tipo" },
                values: new object[,]
                {
                    { 1, "Agregar opcion nueva" },
                    { 2, "Soporte sobre opcion existente" },
                    { 3, "Instalacion de software" },
                    { 4, "Solicitud de visita" },
                    { 5, "Solicitud de entrenamiento" },
                    { 6, "Soporte de hardware" },
                    { 7, "Otros" }
                });

            migrationBuilder.InsertData(
                table: "Clientes",
                columns: new[] { "ClienteId", "Clave", "ConfiguracionId", "Nombres" },
                values: new object[,]
                {
                    { 1, "xtZTnA23", 13, "Samuel" },
                    { 2, "atDt23", 13, "Rafa" },
                    { 3, "mnQOJv23", 13, "Jeison" }
                });

            migrationBuilder.CreateIndex(
                name: "IX_Clientes_ConfiguracionId",
                table: "Clientes",
                column: "ConfiguracionId");

            migrationBuilder.CreateIndex(
                name: "IX_Respuestas_ClienteId",
                table: "Respuestas",
                column: "ClienteId");

            migrationBuilder.CreateIndex(
                name: "IX_Respuestas_TicketId",
                table: "Respuestas",
                column: "TicketId");

            migrationBuilder.CreateIndex(
                name: "IX_Tickets_ClienteId",
                table: "Tickets",
                column: "ClienteId");

            migrationBuilder.CreateIndex(
                name: "IX_Tickets_EstatusId",
                table: "Tickets",
                column: "EstatusId");

            migrationBuilder.CreateIndex(
                name: "IX_Tickets_PrioridadId",
                table: "Tickets",
                column: "PrioridadId");

            migrationBuilder.CreateIndex(
                name: "IX_Tickets_SistemaId",
                table: "Tickets",
                column: "SistemaId");

            migrationBuilder.CreateIndex(
                name: "IX_Tickets_TipoId",
                table: "Tickets",
                column: "TipoId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Respuestas");

            migrationBuilder.DropTable(
                name: "Tickets");

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

            migrationBuilder.DropTable(
                name: "Configuraciones");
        }
    }
}
